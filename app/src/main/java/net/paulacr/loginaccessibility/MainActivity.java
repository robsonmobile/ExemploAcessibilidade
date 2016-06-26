package net.paulacr.loginaccessibility;

import android.app.UiAutomation;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.LinearLayout;

import net.paulacr.loginaccessibility.customview.ArrowView;
import net.paulacr.loginaccessibility.customview.LovelyView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AccessibilityManager accessibilityManager = (AccessibilityManager)
                getSystemService(Context.ACCESSIBILITY_SERVICE);

        Log.i("Log accessibility", "is enabled" + accessibilityManager.isEnabled());
        Log.i("Log accessibility", "is touch enabled" + accessibilityManager.isTouchExplorationEnabled());


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.socialNetworkConnectLayout);
        linearLayout.setContentDescription("conectar com redes sociais");
        ViewCompat.setImportantForAccessibility(linearLayout, ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);


        Button btn = (Button) findViewById(R.id.buttonConnect);
        assert btn != null;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setAccessibilityTraversalBefore(R.id.usernameInputLayout);
        }

        ArrowView arrowView = (ArrowView) findViewById(R.id.arrow);
        arrowView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_HOVER_ENTER);
        

        if(accessibilityManager.isEnabled()) {

        }
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return super.dispatchPopulateAccessibilityEvent(event);
    }



}
