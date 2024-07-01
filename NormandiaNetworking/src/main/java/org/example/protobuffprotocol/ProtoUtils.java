package org.example.protobuffprotocol;
import org.example.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ProtoUtils {
    public static NormandiaProtobufs.Request createLoginRequest(User user)
    {
    NormandiaProtobufs.User userDTO = NormandiaProtobufs.User.newBuilder().setId(user.getId().intValue()).setNume(user.getUsername()).setPassword(user.getPassword()).build();
    NormandiaProtobufs.Request request=NormandiaProtobufs.Request.newBuilder().setType(NormandiaProtobufs.Request.Type.LOGIN).setClient(userDTO).build();
    return request;
    }
       public static NormandiaProtobufs.Request createLogoutResponse(User user) {
        NormandiaProtobufs.User userDTO = NormandiaProtobufs.User.newBuilder().setId(user.getId().intValue()).setNume(user.getUsername()).setPassword(user.getPassword()).build();
        NormandiaProtobufs.Request request = NormandiaProtobufs.Request.newBuilder().setType(NormandiaProtobufs.Request.Type.LOGOUT).setClient(userDTO).build();
        return request;
        }
        public static NormandiaProtobufs.Request createGetCurseRequest(Cursa cursa)
        {
        NormandiaProtobufs.Cursa cursaDTO = NormandiaProtobufs.Cursa.newBuilder().setId(cursa.getId().intValue()).setDestinatie(cursa.getDestinatie()).setData(cursa.getDate().toString()).setOra(cursa.getOra().toString()).build();
        NormandiaProtobufs.Request request=NormandiaProtobufs.Request.newBuilder().setType(NormandiaProtobufs.Request.Type.GET_CURSE).setCursa(cursaDTO).build();
        return request;
        }
        public static NormandiaProtobufs.Request createGetSeatsRequest(Seat seat)
        {
        NormandiaProtobufs.Seat seatDTO = NormandiaProtobufs.Seat.newBuilder().setId(seat.getId().intValue()).setNrloc(seat.getSeatNumber()).setIdRezervare(seat.getRezervare().getId().intValue()).setId(seat.getId().intValue()).build();
        NormandiaProtobufs.Request request=NormandiaProtobufs.Request.newBuilder().setType(NormandiaProtobufs.Request.Type.GET_SEATS).setSeat(seatDTO).build();
        return request;
        }
        public static NormandiaProtobufs.Request createGetRezervariRequest(Rezervare rezervare)
        {
        NormandiaProtobufs.Rezervare rezervareDTO = NormandiaProtobufs.Rezervare.newBuilder().setId(rezervare.getId().intValue()).setIdClient(rezervare.getClient().getId().intValue()).setIdCursa(rezervare.getCursa().getId().intValue()).setNrlocuri(rezervare.getLocuri()).build();
        NormandiaProtobufs.Request request=NormandiaProtobufs.Request.newBuilder().setType(NormandiaProtobufs.Request.Type.GET_REZERVARI).setRezervare(rezervareDTO).build();
        return request;
        }
        public static NormandiaProtobufs.Request createGetUsersRequest(User user)
        {
        NormandiaProtobufs.User userDTO = NormandiaProtobufs.User.newBuilder().setId(user.getId().intValue()).setNume(user.getUsername()).setPassword(user.getPassword()).build();
        NormandiaProtobufs.Request request=NormandiaProtobufs.Request.newBuilder().setType(NormandiaProtobufs.Request.Type.GET_USERS).setClient(userDTO).build();
        return request;
        }
        public static NormandiaProtobufs.Request createRegisterRezervareRequest(Rezervare rezervare)
        {
            NormandiaProtobufs.Rezervare rezervareDTO = NormandiaProtobufs.Rezervare.newBuilder()
                    .setId(rezervare.getId().intValue())
                    .setIdClient(rezervare.getClient().getId().intValue())
                    .setIdCursa(rezervare.getCursa().getId().intValue())
                    .setNrlocuri(rezervare.getLocuri())
                    .build();

            // Create and return the request
            NormandiaProtobufs.Request request = NormandiaProtobufs.Request.newBuilder()
                    .setType(NormandiaProtobufs.Request.Type.REGISTER_REZERVARE)
                    .setRezervare(rezervareDTO)
                    .build();

            return request;
        }
        public static NormandiaProtobufs.Request createADDSeatRequest(Seat seat)
        {
            NormandiaProtobufs.Seat seatDTO = NormandiaProtobufs.Seat.newBuilder()
                    .setId(seat.getId().intValue())
                    .setNrloc(seat.getSeatNumber())
                    .setIdRezervare(seat.getRezervare().getId().intValue())
                    .setId(seat.getId().intValue())
                    .build();

            // Create and return the request
            NormandiaProtobufs.Request request = NormandiaProtobufs.Request.newBuilder()
                    .setType(NormandiaProtobufs.Request.Type.ADD_SEAT)
                    .setSeat(seatDTO)
                    .build();

            return request;
        }
        public static NormandiaProtobufs.Request createFindRezervareRequest(User u,Cursa c,int locuri)
        {
            NormandiaProtobufs.User userDTO = NormandiaProtobufs.User.newBuilder().setId(u.getId().intValue()).setNume(u.getUsername()).setPassword(u.getPassword()).build();
            NormandiaProtobufs.Cursa cursaDTO = NormandiaProtobufs.Cursa.newBuilder().setId(c.getId().intValue()).setDestinatie(c.getDestinatie()).setData(c.getDate().toString()).setOra(c.getOra().toString()).build();
            NormandiaProtobufs.Rezervare rezervareDTO = NormandiaProtobufs.Rezervare.newBuilder().setIdClient(u.getId().intValue()).setIdCursa(c.getId().intValue()).setNrlocuri(locuri).build();
            NormandiaProtobufs.Request request=NormandiaProtobufs.Request.newBuilder().setType(NormandiaProtobufs.Request.Type.FIND_REZERVARE).setClient(userDTO).setCursa(cursaDTO).setRezervare(rezervareDTO).build();
            return request;
        }
        public static NormandiaProtobufs.Response createOkResponse()
        {
            NormandiaProtobufs.Response response=NormandiaProtobufs.Response.newBuilder().setType(NormandiaProtobufs.Response.Type.OK).build();
            return response;
        }
        public static NormandiaProtobufs.Response createErrorResponse(String text)
        {
            NormandiaProtobufs.Response response = NormandiaProtobufs.Response.newBuilder().setType(NormandiaProtobufs.Response.Type.ERROR).build();
            return response;
        }
        public static NormandiaProtobufs.Response createGetCurseResponse(Cursa cursa)
        {
            NormandiaProtobufs.Cursa cursaDTO = NormandiaProtobufs.Cursa.newBuilder().setId(cursa.getId().intValue()).setDestinatie(cursa.getDestinatie()).setData(cursa.getDate().toString()).setOra(cursa.getOra().toString()).build();
            NormandiaProtobufs.Response response=NormandiaProtobufs.Response.newBuilder().setType(NormandiaProtobufs.Response.Type.GET_CURSE).setCursa(cursaDTO).build();
            return response;
        }
        public static NormandiaProtobufs.Response createGetSeatsResponse(Seat seat)
        {
            NormandiaProtobufs.Seat seatDTO = NormandiaProtobufs.Seat.newBuilder().setId(seat.getId().intValue()).setNrloc(seat.getSeatNumber()).setIdRezervare(seat.getRezervare().getId().intValue()).setId(seat.getId().intValue()).build();
            NormandiaProtobufs.Response response=NormandiaProtobufs.Response.newBuilder().setType(NormandiaProtobufs.Response.Type.GET_SEATS).setSeat(seatDTO).build();
            return response;
        }
        public static NormandiaProtobufs.Response createGetRezervariResponse(Rezervare rezervare)
        {
            NormandiaProtobufs.Rezervare rezervareDTO = NormandiaProtobufs.Rezervare.newBuilder().setId(rezervare.getId().intValue()).setIdClient(rezervare.getClient().getId().intValue()).setIdCursa(rezervare.getCursa().getId().intValue()).setNrlocuri(rezervare.getLocuri()).build();
            NormandiaProtobufs.Response response=NormandiaProtobufs.Response.newBuilder().setType(NormandiaProtobufs.Response.Type.GET_REZERVARI).setRezervare(rezervareDTO).build();
            return response;
        }
        public static NormandiaProtobufs.Response createGetUsersResponse(User user)
        {
            NormandiaProtobufs.User userDTO = NormandiaProtobufs.User.newBuilder().setId(user.getId().intValue()).setNume(user.getUsername()).setPassword(user.getPassword()).build();
            NormandiaProtobufs.Response response=NormandiaProtobufs.Response.newBuilder().setType(NormandiaProtobufs.Response.Type.GET_USERS).setClient(userDTO).build();
            return response;
        }


        public static NormandiaProtobufs.Response createFindRezervareResponse(Rezervare rezervare)
        {
            NormandiaProtobufs.Rezervare rezervareDTO = NormandiaProtobufs.Rezervare.newBuilder().setId(rezervare.getId().intValue()).setIdClient(rezervare.getClient().getId().intValue()).setIdCursa(rezervare.getCursa().getId().intValue()).setNrlocuri(rezervare.getLocuri()).build();
            NormandiaProtobufs.Response response=NormandiaProtobufs.Response.newBuilder().setType(NormandiaProtobufs.Response.Type.FIND_REZERVARE).setRezervare(rezervareDTO).build();
            return response;
        }
        /*public static User getUser(NormandiaProtobufs.User userDTO)
        {
          /*  Long id = (long) userDTO.getId();
            String nume = userDTO.getNume();
            String password = userDTO.getPassword();
            User user = new User(nume,password);
            user.setId(id);
            return user;
        }*/


        

}
