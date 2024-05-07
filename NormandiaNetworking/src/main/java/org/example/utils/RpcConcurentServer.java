package org.example.utils;

import org.example.NormandiaClientRpcWorker;
import org.example.IRezervareServices;

import java.net.Socket;
import java.security.Provider;

public class RpcConcurentServer extends AbstractConcurentServer {

    private IRezervareServices competitionServices;

    public RpcConcurentServer(int port, IRezervareServices competitionServices) {
        super(port);
        this.competitionServices = competitionServices;
        System.out.println("RPCConcurrentServer");
    }
    @Override
    protected Thread createWorker(Socket client) {
        NormandiaClientRpcWorker worker = new NormandiaClientRpcWorker(competitionServices, client);
        Thread tw = new Thread(worker);
        return tw;
    }
}
