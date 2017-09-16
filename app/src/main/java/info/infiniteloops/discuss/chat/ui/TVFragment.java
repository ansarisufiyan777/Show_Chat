package info.infiniteloops.discuss.chat.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import info.infiniteloops.discuss.R;
import info.infiniteloops.discuss.Utilities;
import info.infiniteloops.discuss.chat.data.FriendDB;
import info.infiniteloops.discuss.chat.data.GroupDB;
import info.infiniteloops.discuss.chat.data.StaticConfig;
import info.infiniteloops.discuss.chat.model.Group;
import info.infiniteloops.discuss.chat.model.ListFriend;
import info.infiniteloops.discuss.chat.util.ViewTarget;


public class TVFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerListGroups;
    private ArrayList<Group> listGroup;
    private ListTVAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public static final int REQUEST_EDIT_GROUP = 0;


    public TVFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.chat_fragment_group, container, false);

        listGroup = GroupDB.getInstance(getContext()).getListGroups();
        recyclerListGroups = (RecyclerView) layout.findViewById(R.id.recycleListGroup);
        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerListGroups.setLayoutManager(layoutManager);
        adapter = new ListTVAdapter(getContext(), listGroup);
        recyclerListGroups.setAdapter(adapter);


        if(listGroup.size() == 0){
            //Ket noi server hien thi group
            mSwipeRefreshLayout.setRefreshing(true);
            getListGroup();
        }
        return layout;
    }

    private void getListGroup(){
        Utilities.getFirebaseDBReference().child("user/"+ StaticConfig.UID+"/subscribed/tvs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {


                    if (dataSnapshot.getValue() != null) {
                        HashMap mapListGroup = (HashMap) dataSnapshot.getValue();
                        Iterator iterator = mapListGroup.keySet().iterator();
                        while (iterator.hasNext()) {
                            String idGroup = mapListGroup.get(iterator.next().toString()).toString();
                            Group newGroup = new Group();
                            newGroup.id = idGroup;
                            listGroup.add(newGroup);
                        }
                        getGroupInfo(0);
                    } else {
                        mSwipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_EDIT_GROUP && resultCode == Activity.RESULT_OK) {
            listGroup.clear();
            ListTVAdapter.listFriend = null;
            GroupDB.getInstance(getContext()).dropDB();
            getListGroup();
        }
    }

    private void getGroupInfo(final int indexGroup){
        if(indexGroup == listGroup.size()){
            adapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }else {
            Utilities.getFirebaseDBReference().child("tvs/"+listGroup.get(indexGroup).id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue() != null){
                        HashMap mapGroup = (HashMap) dataSnapshot.getValue();

                        HashMap member= (HashMap) mapGroup.get("member");
                        Iterator iterator = member.keySet().iterator();

                        HashMap mapGroupInfo = (HashMap) mapGroup.get("groupInfo");

                        while (iterator.hasNext()){
                            String idMember =  member.get(iterator.next().toString()).toString();
                            listGroup.get(indexGroup).member.add(idMember);
                        }


                        listGroup.get(indexGroup).groupInfo.put("name", (String) mapGroupInfo.get("originalTitle"));
                        listGroup.get(indexGroup).groupInfo.put("admin", "Hellow World");
                        listGroup.get(indexGroup).groupInfo.put("poster",
                                Utilities.POSTER_IMAGE_BASE_URL
                                + Utilities.POSTER_IMAGE_SIZE
                                +(String) mapGroupInfo.get("posterPath"));

                    }
                    Log.d("TVFragment", listGroup.get(indexGroup).id +": " + dataSnapshot.toString());
                    getGroupInfo(indexGroup +1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onRefresh() {
        listGroup.clear();
        ListTVAdapter.listFriend = null;
        GroupDB.getInstance(getContext()).dropDB();
        adapter.notifyDataSetChanged();
        getListGroup();
    }

    public class FragGroupClickFloatButton implements View.OnClickListener{

        Context context;
        public FragGroupClickFloatButton getInstance(Context context){
            this.context = context;
            return this;
        }

        @Override
        public void onClick(View view) {
            startActivity(new Intent(getContext(), AddGroupActivity.class));
        }
    }
}

class ListTVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Group> listGroup;
    public static ListFriend listFriend = null;
    private Context context;

    public ListTVAdapter(Context context, ArrayList<Group> listGroup){
        this.context = context;
        this.listGroup = listGroup;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_rc_item_group, parent, false);
        return new ItemTVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final String groupName = listGroup.get(position).groupInfo.get("name");
        if(groupName != null && groupName.length() > 0) {
            ((ItemTVViewHolder) holder).txtGroupName.setText(groupName);
            ((ItemTVViewHolder) holder).iconGroup.setText((groupName.charAt(0) + "").toUpperCase());

        }
        ViewTarget viewTarget = new ViewTarget(((ItemTVViewHolder) holder).groupBG);
        Glide.with(context).load(listGroup.get(position).groupInfo.get("poster")).fitCenter().centerCrop().into(viewTarget);

        ((ItemTVViewHolder) holder).btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setTag(new Object[]{groupName, position});
                view.getParent().showContextMenuForChild(view);
            }
        });
        ((RelativeLayout)((ItemTVViewHolder) holder).txtGroupName.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listFriend == null){
                    listFriend = FriendDB.getInstance(context).getListFriend();
                }
                ArrayList<CharSequence> idFriend = new ArrayList<>();
                ChatActivity.bitmapAvataFriend = new HashMap<>();
                for(String id : listGroup.get(position).member) {
                    idFriend.add(id);
                    String avata = listFriend.getAvataById(id);
                    if(!avata.equals(StaticConfig.STR_DEFAULT_BASE64)) {
                        byte[] decodedString = Base64.decode(avata, Base64.DEFAULT);
                        ChatActivity.bitmapAvataFriend.put(id, BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
                    }else if(avata.equals(StaticConfig.STR_DEFAULT_BASE64)) {
                        ChatActivity.bitmapAvataFriend.put(id, BitmapFactory.decodeResource(context.getResources(), R.drawable.default_avata));
                    }else {
                        ChatActivity.bitmapAvataFriend.put(id, null);
                    }
                }
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra(StaticConfig.INTENT_KEY_CHAT_FRIEND, groupName);
                intent.putCharSequenceArrayListExtra(StaticConfig.INTENT_KEY_CHAT_ID, idFriend);
                intent.putExtra(StaticConfig.INTENT_KEY_CHAT_ROOM_ID, listGroup.get(position).id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGroup.size();
    }
}

class ItemTVViewHolder extends RecyclerView.ViewHolder {
    public TextView iconGroup, txtGroupName;
    public ImageButton btnMore;
    public RelativeLayout groupBG;
    public ItemTVViewHolder(View itemView) {
        super(itemView);
        iconGroup = (TextView) itemView.findViewById(R.id.icon_group);
        txtGroupName = (TextView) itemView.findViewById(R.id.txtName);
        btnMore = (ImageButton) itemView.findViewById(R.id.btnMoreAction);
        groupBG = (RelativeLayout) itemView.findViewById(R.id.groupBG);
    }

}