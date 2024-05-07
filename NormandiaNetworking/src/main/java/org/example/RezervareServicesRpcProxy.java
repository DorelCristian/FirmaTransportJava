package org.example;

//import ro.mpp.dto.RegisterParticipantDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RezervareServicesRpcProxy implements IRezervareServices{


    private String host;
    private int port;

    private IRezervareObserver client;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    private Socket connection;

    private volatile boolean finished;

    private BlockingQueue<Response> queueResponses;

    public RezervareServicesRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        queueResponses = new LinkedBlockingQueue<>();
    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
            connection.close();
            client = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void startReader() {
        Thread tw = new Thread(new ReaderThread());
        tw.start();
    }

    @Override
    public User connect(String username, String password, IRezervareObserver client) {
        initializeConnection();//
        User user = new User(username, password);
        Request request = new Request.Builder().type(RequestType.LOGIN).data(user).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.OK) {
            this.client = client;
            return (User) response.getData();
        }
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            closeConnection();
//            throw new IllegalArgumentException(error);
        }

        return null;
    }

    @Override
    public Rezervare findRezervare(User user, Cursa cursa, Integer locuri) {
        Request request = new Request.Builder().type(RequestType.FIND_REZERVARE).data(new Rezervare(user,cursa,locuri)).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            throw new IllegalArgumentException(error);
        }
        return null;
    }


    @Override
    public void logout(String username) {
        Request request = new Request.Builder().type(RequestType.LOGOUT).data(username).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            throw new IllegalArgumentException(error);
        }

    }

    @Override
    public List<User> getAllUsers() {
        Request request = new Request.Builder().type(RequestType.GET_USERS).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            throw new IllegalArgumentException(error);
        }
        return (List<User>) response.getData();
      //  return null;
    }

   /* @Override
    public void registerParticipant(String name, LocalDate birthDate, List<Long> competitionsId) {
        RegisterParticipantDTO participant = new RegisterParticipantDTO(name, birthDate,competitionsId);
        Request request = new Request.Builder().type(RequestType.REGISTER_PARTICIPANT).data(participant).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            throw new IllegalArgumentException(error);
        }

    }*/

    /*@Override
    public Iterable<User> getAllUsers() {

        return null;
    }*/

    @Override
    public List<Rezervare> getAllRezervari() {
        Request request=new Request.Builder().type(RequestType.GET_REZERVARI).build();
        sendRequest(request);
        Response response=readResponse();
        if(response.getType()==ResponseType.ERROR){
            String error=response.getData().toString();
            throw new IllegalArgumentException(error);
        }
        return (List<Rezervare>) response.getData();
       // return null;
    }

    @Override
    public List<Cursa> getAllCurse() {
        Request request=new Request.Builder().type(RequestType.GET_CURSE).build();
        sendRequest(request);
        Response response=readResponse();
        if(response.getType()==ResponseType.ERROR){
            String error=response.getData().toString();
            throw new IllegalArgumentException(error);
        }
        return (List<Cursa>) response.getData();
       /* List<Cursa>b=new ArrayList<>();
        Cursa c1=new Cursa("Bucuresti", Date.valueOf(LocalDateTime.now().toLocalDate()),new Time(LocalDateTime.now().getHour(),LocalDateTime.now().getMinute(),LocalDateTime.now().getSecond()));
        b.add(c1);*/
       // return b;


       // return null;
    }

    @Override
    public List<Seat> getAllSeats(Rezervare rezervare) {
        Request request=new Request.Builder().type(RequestType.GET_SEATS).data(rezervare).build();
        sendRequest(request);
        Response response=readResponse();
        if(response.getType()==ResponseType.ERROR){
            String error=response.getData().toString();
            throw new IllegalArgumentException(error);
        }
        return (List<Seat>) response.getData();
       // return null;
    }

    /*@Override
    public List<Seat> getAllSeats(Long id) {
        Request request=new Request.Builder().type(RequestType.GET_SEATS).data(id).build();
        sendRequest(request);
        Response response=readResponse();
        if(response.getType()==ResponseType.ERROR){
            String error=response.getData().toString();
            throw new IllegalArgumentException(error);
        }
        return (List<Seat>) response.getData();
      //  return null;
    }*/

    @Override
    public void registerRezervare(User client, Cursa cursa, int locuri) {
        Rezervare rezervare=new Rezervare(client,cursa,locuri);
        Request request=new Request.Builder().type(RequestType.REGISTER_REZERVARE).data(rezervare).build();
        sendRequest(request);
        Response response=readResponse();
        if(response.getType()==ResponseType.ERROR){
            String error=response.getData().toString();
            throw new IllegalArgumentException(error);
        }




    }

    @Override
    public void addSeat(Rezervare rezervare, Integer seat) {
        Seat s=new Seat(rezervare,seat);
        Request request=new Request.Builder().type(RequestType.ADD_SEAT).data(s).build();
        sendRequest(request);
        Response response=readResponse();
        if(response.getType()==ResponseType.ERROR){
            String error=response.getData().toString();
            throw new IllegalArgumentException(error);
        }
    }

   /* @Override
    public AllCompetitionsDTO getAllCompetitionsWithNrParticipants() {
        Request request = new Request.Builder().type(RequestType.GET_COMPETITIONS).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            throw new IllegalArgumentException(error);
        }
        return (AllCompetitionsDTO) response.getData();
    }*/

   /* @Override
    public Map<Participant, List<Long>> getAllParticipantsByCompetition(Long id) {
        Request request = new Request.Builder().type(RequestType.GET_PARTICIPANTS_BY_COMPETITION).data(id).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            throw new IllegalArgumentException(error);
        }
        return (Map<Participant, List<Long>>) response.getData();

    }*/







    private  void  sendRequest(Request request) {
        try {
            output.writeObject(request);
            output.flush();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error sending object " + e);
        }
    }

    private synchronized Response readResponse() {
        Response response = null;
        try {
            response = queueResponses.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }


    private void handleUpdate(Response response) {
        if (response.getType() == ResponseType.UPDATE) {
           // client.registerParticipant();
            client.registerRezervare();
        }
    }
    private boolean isUpdateResponse(Response response) {
        return response.getType() == ResponseType.UPDATE;
    }
    private class ReaderThread implements Runnable {
        public void run() {
            while (!finished) {
                try {
                    Object response = input.readObject();
                    System.out.println("Response received " + response);
                    if (response instanceof Response) {
                        Response response1 = (Response) response;
                        if (isUpdateResponse(response1)) {
                            handleUpdate(response1);
                        } else {
                            try {
                                queueResponses.put((Response) response);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Reading error " + e);
                }
            }
        }
    }
}
