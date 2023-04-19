package correo;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

public class WebViewPanel extends JPanel {
    private JFXPanel jfxPanel;
    private WebView webView;
    private String contenido;
    
    public WebViewPanel(String contenido) {
        this.contenido = contenido;
        setLayout(new BorderLayout());
        jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.CENTER);

        Platform.runLater(() -> {
            webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
        });
    }

    public void loadContent() {
        Platform.runLater(() -> {
            webView.getEngine().loadContent(contenido);
        });
    }
}
