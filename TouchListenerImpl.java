package com.example.lovablelumber;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
/*
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
*/
import org.jetbrains.annotations.NotNull;

/*
@Metadata(
        mv = {1, 1, 16},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"},
        d2 = {"Lcom/android/raywenderlich/jokes/TouchListenerImpl;", "Landroid/view/View$OnTouchListener;", "minWidth", "", "minHeight", "(II)V", "lastDiffX", "", "lastDiffY", "getMinHeight", "()I", "getMinWidth", "originUp", "", "originX", "originY", "secondOriginUp", "secondOriginX", "secondOriginY", "onTouch", "view", "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "app_debug"}
)

 */
public final class TouchListenerImpl implements OnTouchListener {
    private float originX;
    private float originY;
    private float secondOriginX;
    private float secondOriginY;
    private float lastDiffX;
    private float lastDiffY;
    private boolean originUp;
    private boolean secondOriginUp;
    private final int minWidth;
    private final int minHeight;

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(@NotNull View view, @NotNull MotionEvent event) {
       // Intrinsics.checkParameterIsNotNull(view, "view");
       // Intrinsics.checkParameterIsNotNull(event, "event");
        event.offsetLocation(event.getRawX() - event.getX(), event.getRawY() - event.getY());
        switch(event.getActionMasked()) {
            case 0:
                this.originUp = false;
                this.secondOriginUp = false;
                this.originX = view.getX() - event.getX(0);
                this.originY = view.getY() - event.getY(0);
                break;
            case 1:
                this.originUp = true;
                break;
            case 2:
                float newX;
                float newY;
                ViewParent var10000;
                if (event.getPointerCount() == 2) {
                    newX = Math.abs(event.getX(1) - event.getX(0));
                    newY = Math.abs(event.getY(1) - event.getY(0));
                    int newWidth = (int)(newX * (float)view.getMeasuredWidth() / this.lastDiffX);
                    int newHeight = (int)(newY * (float)view.getMeasuredHeight() / this.lastDiffY);
                    if (newWidth > this.minWidth && newHeight > this.minHeight) {
                        var10000 = view.getParent();
                        if (var10000 == null) {
                            //throw new TypeCastException("null cannot be cast to non-null type android.view.View");
                        }

                        int parentWidth = ((View)var10000).getWidth();
                        var10000 = view.getParent();
                        if (var10000 == null) {
                            //throw new TypeCastException("null cannot be cast to non-null type android.view.View");
                        }

                        int parentHeight = ((View)var10000).getHeight();
                        LayoutParams params = view.getLayoutParams();
                        if ((float)newWidth + view.getX() > (float)parentWidth) {
                            newWidth = parentWidth - (int)view.getX();
                        }

                        if ((float)newHeight + view.getY() > (float)parentHeight) {
                            newHeight = parentHeight - (int)view.getY();
                        }

                        params.width = newWidth;
                        params.height = newHeight;
                        view.setLayoutParams(params);
                        this.lastDiffX = newX;
                        this.lastDiffY = newY;
                    }
                } else if (!this.originUp && !this.secondOriginUp) {
                    newX = event.getX(0) + this.originX;
                    newY = event.getY(0) + this.originY;
                    if (newX < (float)0) {
                        newX = 0.0F;
                    }

                    if (newY < (float)0) {
                        newY = 0.0F;
                    }

                    float var10 = newX + (float)view.getMeasuredWidth();
                    ViewParent var10001 = view.getParent();
                    if (var10001 == null) {
                        //throw new TypeCastException("null cannot be cast to non-null type android.view.View");
                    }

                    if (var10 > (float)((View)var10001).getWidth()) {
                        var10000 = view.getParent();
                        if (var10000 == null) {
                            //throw new TypeCastException("null cannot be cast to non-null type android.view.View");
                        }

                        newX = (float)((View)var10000).getWidth() - (float)view.getMeasuredWidth();
                    }

                    var10 = newY + (float)view.getMeasuredHeight();
                    var10001 = view.getParent();
                    if (var10001 == null) {
                        //throw new TypeCastException("null cannot be cast to non-null type android.view.View");
                    }

                    if (var10 > (float)((View)var10001).getHeight()) {
                        var10000 = view.getParent();
                        if (var10000 == null) {
                            //throw new TypeCastException("null cannot be cast to non-null type android.view.View");
                        }

                        newY = (float)((View)var10000).getHeight() - (float)view.getMeasuredHeight();
                    }

                    view.setX(newX);
                    view.setY(newY);
                }
            case 3:
            case 4:
            default:
                break;
            case 5:
                this.secondOriginX = view.getX() - event.getX(1);
                this.secondOriginY = view.getY() - event.getY(1);
                this.lastDiffX = Math.abs(this.secondOriginX - this.originX);
                this.lastDiffY = Math.abs(this.secondOriginY - this.originY);
                break;
            case 6:
                this.secondOriginUp = true;
        }

        return true;
    }

    public final int getMinWidth() {
        return this.minWidth;
    }

    public final int getMinHeight() {
        return this.minHeight;
    }

    public TouchListenerImpl(int minWidth, int minHeight) {
        this.minWidth = minWidth;
        this.minHeight = minHeight;
    }
}
