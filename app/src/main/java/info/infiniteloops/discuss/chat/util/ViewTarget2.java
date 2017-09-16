package info.infiniteloops.discuss.chat.util;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import com.bumptech.glide.request.animation.GlideAnimation;

public abstract class ViewTarget2<Z> extends com.bumptech.glide.request.target.ViewTarget<ViewGroup,Z> implements GlideAnimation.ViewAdapter {


    public ViewTarget2(ViewGroup view) {
        super(view);
    }

    @Override
    public Drawable getCurrentDrawable() {
        return view.getBackground();
    }

    /**
     * Sets the given {@link Drawable} on the view using
     * {@link android.widget.ImageView#setImageDrawable(Drawable)}.
     *
     * @param drawable {@inheritDoc}
     */
    @Override
    public void setDrawable(Drawable drawable) {
        view.setBackground(drawable);
    }

    /**
     * Sets the given {@link Drawable} on the view using
     * {@link android.widget.ImageView#setImageDrawable(Drawable)}.
     *
     * @param placeholder {@inheritDoc}
     */
    @Override
    public void onLoadStarted(Drawable placeholder) {
        view.setBackground(placeholder);
    }

    /**
     * Sets the given {@link Drawable} on the view using
     * {@link android.widget.ImageView#setImageDrawable(Drawable)}.
     *
     * @param errorDrawable {@inheritDoc}
     */
    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        view.setBackground(errorDrawable);
    }

    /**
     * Sets the given {@link Drawable} on the view using
     * {@link android.widget.ImageView#setImageDrawable(Drawable)}.
     *
     * @param placeholder {@inheritDoc}
     */
    @Override
    public void onLoadCleared(Drawable placeholder) {
        view.setBackground(placeholder);
    }

    @Override
    public void onResourceReady(Z resource, GlideAnimation<? super Z> glideAnimation) {

        this.setResource(resource);
    }

    protected abstract void setResource(Z resource);

}