/*package org.example.protobuffprotocol;

//import ro.mpp.dto.RegisterParticipantDTO;

import org.example.IRezervareObserver;
import org.example.IRezervareServices;
import org.example.RequestType;
import org.example.ResponseType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RPCWorkerProto implements Runnable, IRezervareObserver {

    private IRezervareServices server;

    private Socket connection;

    private ObjectInputStream input;

    private ObjectOutputStream output;

    private volatile boolean connected;


    public RPCWorkerProto(IRezervareServices competitionServices, Socket connection) {
        this.server = competitionServices;
        this.connection = connection;
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
                Response response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            input.close();
            output.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

    }

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();


    private Response handleRequest(Request request) {
        Response response = null;
        if (request.getType() == RequestType.LOGIN) {
            System.out.println("Login request ...");
            User userDTO = (User) request.getData();
            try {
                var optional = server.connect(userDTO.getUsername(), userDTO.getPassword(), this);
                if (optional!=null) {
                    return new Response.Builder().type(ResponseType.OK).data(userDTO).build();
                }
                else{
                    connected = false;
                    return new Response.Builder().type(ResponseType.ERROR).data("Invalid username or passwordaaaaaaa").build();
                }
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.getType() == RequestType.LOGOUT) {
            System.out.println("Logout request ...");
            String username = (String) request.getData();
            try {
                server.logout(username);
                return okResponse;
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.getType() == RequestType.SIGNUP) {
            System.out.println("Register request ...");
            return null;
        }


        if(request.getType()==RequestType.GET_SEATS){
            System.out.println("GetAllSeats request...");
            // Long id=(Long)request.getData();
            Rezervare rezervare=(Rezervare) request.getData();
            try{
                return new Response.Builder().type(ResponseType.GET_SEATS).data(server.getAllSeats(rezervare)).build();
            }
            catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }



        /*if (request.getType() == RequestType.REGISTER_PARTICIPANT) {
            System.out.println("Register Participant request ...");
            RegisterParticipantDTO register = (RegisterParticipantDTO) request.getData();
            try {
                server.registerParticipant(register.getName(), register.getBirthDate(), register.getCompetitionId());
                return okResponse;
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }*/
        /*if (request.getType()==RequestType.REGISTER_REZERVARE){
            System.out.println("Register Rezervare request");
            Rezervare rezervare=(Rezervare) request.getData();
            try {
                server.registerRezervare(rezervare.getClient(),rezervare.getCursa(),rezervare.getLocuri());
                return okResponse;
            }catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }

        }
        if (request.getType()==RequestType.GET_CURSE)
        {
            System.out.println("GetCurse...");
            try{
                return new Response.Builder().type(ResponseType.GET_CURSE).data(server.getAllCurse()).build();

            }
            catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }

        }
        if (request.getType()==RequestType.ADD_SEAT)
        {
            System.out.println("AddSeat...");
            Seat seat=(Seat) request.getData();
            try{
                server.addSeat(seat.getRezervare(),seat.getSeatNumber());
                return okResponse;
            }
            catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.getType()==RequestType.GET_REZERVARI)
        {
            System.out.println("GetRezervari...");
            try{
                return new Response.Builder().type(ResponseType.GET_REZERVARI).data(server.getAllRezervari()).build();
            }
            catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.getType()==RequestType.GET_USERS)
        {
            System.out.println("GetUsers...");
            try{
                return new Response.Builder().type(ResponseType.GET_USERS).data(server.getAllUsers()).build();
            }
            catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.getType()==RequestType.FIND_REZERVARE)
        {
            System.out.println("FindRezervare...");
            Rezervare findRezervareDTO=(Rezervare) request.getData();
            try{
                return new Response.Builder().type(ResponseType.FIND_REZERVARE).data(server.findRezervare(findRezervareDTO.getClient(),findRezervareDTO.getCursa(),findRezervareDTO.getLocuri())).build();
            }
            catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }*/

        /*if (request.getType()==RequestType.GET_PARTICIPANTS_BY_COMPETITION){
            System.out.println("Get Participants by competition request ...");
            Long competitionId = (Long) request.getData();
            try {
                return new Response.Builder().type(ResponseType.GET_PARTICIPANTS_BY_COMPETITION).data(server.getAllParticipantsByCompetition(competitionId)).build();
            } catch (Exception e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }*/

       /* return response;
    }


    private void sendResponse(Response response) {
        try {
            output.writeObject(response);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*@Override
    public void registerParticipant() {
        Response response = new Response.Builder().type(ResponseType.UPDATE).data(null).build();
        sendResponse(response);
    }*/

   /* @Override
    public void registerRezervare() {
        Response response = new Response.Builder().type(ResponseType.UPDATE).data(null).build();
        sendResponse(response);
    }
}*/
