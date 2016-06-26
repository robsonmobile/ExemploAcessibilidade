package net.paulacr.loginaccessibility.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import static android.os.Build.VERSION_CODES.LOLLIPOP;

import net.paulacr.loginaccessibility.R;

/**
 * Created by paularosa on 5/22/16.
 */
public class LovelyView extends LinearLayout {

    private String leftLabel = "";
    private String rightLabel = "";
    private TextView leftTextView;
    private TextView rightTextView;
    private ImageView imageView;
    private int leftStyle ;
    private int rightStyle;

    public LovelyView(Context context) {
        super(context);
        initViews(context, null);
    }

    public LovelyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews(context, attributeSet);
    }

    public LovelyView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        initViews(context, attributeSet);
    }

    @TargetApi(LOLLIPOP)
    public LovelyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context, attrs);
    }

    private void initViews (Context context, AttributeSet attributeSet) throws IllegalArgumentException {

        TypedArray array = context.getTheme().obtainStyledAttributes(attributeSet,
                R.styleable.LovelyView, 0, 0);

        try {
            leftLabel = array.getString(R.styleable.LovelyView_leftLabel);
            rightLabel = array.getString(R.styleable.LovelyView_rigthLabel);
            leftStyle = array.getResourceId(R.styleable.LovelyView_leftLabelStyle, android.R.style.TextAppearance_DeviceDefault);
            rightStyle = array.getResourceId(R.styleable.LovelyView_leftLabel, android.R.style.TextAppearance_DeviceDefault);
            //imageView = array.getInteger(R.styleable.LovelyView_icon, 0);
        } finally {
            array.recycle();
        }

        LayoutInflater.from(context).inflate(R.layout.key_value_layout, this);


        //TextView left
        leftTextView = (TextView) findViewById(R.id.leftTextView);
        leftTextView.setText(leftLabel);
        leftTextView.setTextAppearance(context, leftStyle);

        rightTextView = (TextView) findViewById(R.id.rightTextView);
        rightTextView.setText(rightLabel);
        rightTextView.setTextAppearance(context, rightStyle);
    }

    public String getLeftLabel() {
        return leftLabel;
    }

    public void setLeftLabel(String leftLabel) {
        this.leftLabel = leftLabel;
        if(leftTextView != null) {
            leftTextView.setText(leftLabel);
        }
    }

    public String getRightLabel() {
        return rightLabel;
    }

    public void setRightLabel(String rightLabel) {
        this.rightLabel = rightLabel;
        if(rightTextView != null) {
            rightTextView.setText(rightLabel);
        }
    }

    

    @Override
    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);

        if(event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            event.getText().add("teste acessibilidade");
        }

    }


}
