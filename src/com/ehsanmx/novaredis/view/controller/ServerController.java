package com.ehsanmx.novaredis.view.controller;

import com.ehsanmx.novaredis.core.server.ServerConnection;
import com.ehsanmx.novaredis.model.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    private ServerConnection serverConnection;

    @FXML
    public Label footer;

    @FXML
    protected TextField txtServerName;

    @FXML
    protected TextField txtHost;

    @FXML
    protected TextField txtPort;

    @FXML
    protected TextField txtPassword;

    private MainController mainController;

    public ServerController(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void okAction() {
        Server server = createHydratedServer();
        this.serverConnection.save(server);
        this.mainController.updateLabel("New server was added.");
        this.mainController.updateTreeView(server);
        txtServerName.getScene().getWindow().hide();
    }

    public void cancelAction() {
        txtServerName.getScene().getWindow().hide();
    }

    public void testAction() {
        System.out.println("Test");
    }

    protected Server createHydratedServer() {
        Server server = new Server();
        server.setName(txtServerName.getText());
        server.setHost(txtHost.getText());
        server.setPort(Integer.parseInt(txtPort.getText()));
        server.setPassword(txtPassword.getText());

        return server;
    }

    public void init(MainController mainController) {
        this.mainController = mainController;
    }
}
