package com.ehsanmx.novaredis.view.controller;

import com.ehsanmx.novaredis.core.server.ServerRepository;
import com.ehsanmx.novaredis.model.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    private ServerRepository serverRepository;

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

    @FXML
    protected Button btnTest;

    private MainController mainController;

    public ServerController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void okAction() {
        Server server = createHydratedServer();
        this.serverRepository.save(server);
        this.mainController.updateLabel("New server was added.");
        this.mainController.updateTreeView(server);
        txtServerName.getScene().getWindow().hide();
    }

    public void cancelAction() {
        txtServerName.getScene().getWindow().hide();
    }

    public void testAction() {
        Server server = createHydratedServer();
//        System.out.println(this.serverRepository.testConnection(server));
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
