package net.paulacr.loginaccessibility;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.paulacr.loginaccessibility.customview.ArrowView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * O AccessibilityManager fornece diversas informaçoes importantes
         * de acessibilidade do sistema, como saber quando o talkback
         * está ligado.
         */
        AccessibilityManager accessibilityManager = (AccessibilityManager)
                getSystemService(Context.ACCESSIBILITY_SERVICE);


        /**
         * Neste trecho de código é possivel informar ao talkback que
         * o ViewGroup das redes sociais é importante e deve ser lido
         * pelo talkback.
         */
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.socialNetworkConnectLayout);
        linearLayout.setContentDescription("conectar com redes sociais");
        ViewCompat.setImportantForAccessibility(linearLayout, ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);

        /**
         * Neste trecho de código é possível fazer com que um texto
         * fique visível após clicar no botao, e receba o foco
         * do talkback para ser falado.
         * Esta implementação é muito util quando se trata
         * de toogle buttons, ou quando se quer dar um feedback ao usuario
         * da açao que foi tomada
         */
        Button btn = (Button) findViewById(R.id.buttonConnect);
        final TextView texto = (TextView) findViewById(R.id.texto_confirma_acao_botao);

        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (texto.getVisibility() == View.VISIBLE) {
                    texto.setVisibility(View.GONE);
                } else {
                    texto.setVisibility(View.VISIBLE);
                    texto.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
                }
            }
        });

        /**
         * Este método permite que o fab seja focado anteriormente a
         * outros componentes, já que o FAB é um componente que
         * deve possui uma ação primária (primeira açao ao entrar no app)
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setAccessibilityTraversalBefore(R.id.usernameInputLayout);
        }

        /**
         * Este método é utilizado para personalizar a aplicação conforme
         * a acessibilidade estiver ligada
         */
        if (accessibilityManager.isEnabled()) {
            //SUA IMPLEMENTACAO PARA QUANDO O TALKBACK ESTÁ LIGADO

        }

        /**
         * Este código mostra como enviar um evento de uma Custom View
         * para o framework de acessibilidade
         */
        /*
        ArrowView arrowView = (ArrowView) findViewById(R.id.arrow);
        if(arrowView.isFocused()) {
            arrowView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
        }
        */
    }
}
