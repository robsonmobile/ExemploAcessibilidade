package net.paulacr.loginaccessibility.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/**
 * Exemplo extraído de http://stackoverflow.com/questions/24723040/how-to-create-a-right-facing-arrow-using-xml-shapes-in-android
 */
public class ArrowView extends View {

    private Paint arrowPaint;
    private Path arrowPath;
    private int arrowColor = 0xFF888888;
    private float density;
    private int diameter = 5, diameter_calc, radius_calc;
    private int startX,startY, currentX, currentY;

    public ArrowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        stuff();
    }

    public ArrowView(Context context) {
        super(context);
        stuff();
    }

    private void stuff() {
        //get density dp
        density = getContext().getResources().getDisplayMetrics().scaledDensity;
        diameter_calc = (int) density * diameter;
        radius_calc = diameter /2;

        //create paint
        arrowPaint = new Paint();
        arrowPaint.setAntiAlias(true);
        arrowPaint.setColor(arrowColor);

        arrowPath = new Path();

        this.setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        startX = canvas.getWidth();
        startY = canvas.getHeight()/2;

        canvas.rotate(-45, startX, startY);

        arrowPath.reset();

        currentX = startX;
        currentY = startY;

        arrowPath.moveTo(currentX,  currentY);

        //Lets move up
        currentY = radius_calc;
        arrowPath.lineTo(currentX, currentY);
        //Now draw circle
        currentX-=radius_calc;
        arrowPath.addCircle(currentX, radius_calc, radius_calc, Path.Direction.CCW);
        currentX-=radius_calc;

        arrowPath.lineTo(currentX,currentY);
        // Go to inner side center point
        currentX = startX - diameter_calc;
        currentY = startY - diameter_calc;
        arrowPath.lineTo(currentX,currentY);
        // Go left
        currentX = startX - startY + radius_calc;
        arrowPath.lineTo(currentX, currentY);
        //Draw circle
        currentY+=radius_calc;
        canvas.drawCircle(currentX, currentY, radius_calc, arrowPaint);
        currentY+=radius_calc;
        arrowPath.lineTo(currentX, currentY);
        //Go to start
        arrowPath.lineTo(startX, startY);

        canvas.drawPath(arrowPath, arrowPaint);

    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        event.getText().add("texto de acessibilidade");
        return true;
    }
}
