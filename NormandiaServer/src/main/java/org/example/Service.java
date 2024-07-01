package org.example;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Service implements IRezervareServices {

    private UserHibernateRepository userDBRepository;

    private ClientDBRepository clientDBRepository;
    private CursaDBRepository cursaDBRepository;
    private RezervareDBRepository rezervareDBRepository;
    private SeatDBRepository seatDBRepository;
    private Map<String, IRezervareObserver> loggedClients;

    private final int defaultThreadsNo=5;

   /* private CompetitionDBRepository competitionDBRepository;
    private ParticipantDBRepository participantDBRepository;
    private RegistrationDBRepository registrationDBRepository;*/


    /*public Service(UserDBRepository userDBRepository, CompetitionDBRepository competitionDBRepository, ParticipantDBRepository participantDBRepository, RegistrationDBRepository registrationDBRepository){
        this.userDBRepository=userDBRepository;
        this.competitionDBRepository=competitionDBRepository;
        this.participantDBRepository=participantDBRepository;
        this.registrationDBRepository=registrationDBRepository;

    }*/

    public Service(UserHibernateRepository userDBRepository, ClientDBRepository clientDBRepository, CursaDBRepository cursaDBRepository, RezervareDBRepository rezervareDBRepository, SeatDBRepository seatDBRepository) {
        this.userDBRepository = userDBRepository;
        this.clientDBRepository = clientDBRepository;
        this.cursaDBRepository = cursaDBRepository;
        this.rezervareDBRepository = rezervareDBRepository;
        this.seatDBRepository = seatDBRepository;
        loggedClients= new ConcurrentHashMap<>();

    }

    public List<User> getAllUsers(){
        return userDBRepository.findAll();
    }

    @Override
    public List<Rezervare> getAllRezervari() {
        return rezervareDBRepository.findAll();
        //return null;
    }

    @Override
    public List<Cursa> getAllCurse() {
        return cursaDBRepository.findAll();

       // return null;
    }

    @Override
    public List<Seat> getAllSeats(Rezervare r) {
return seatDBRepository.findByRezervare(r);

    }

    @Override
    public void registerRezervare(User client, Cursa cursa, int locuri) {
        Rezervare t=new Rezervare(client,cursa,locuri);
       rezervareDBRepository.save(t);
        notifyClients();
    }

    @Override
    public void addSeat(Rezervare rezervare, Integer seat) {
        Seat s=new Seat(rezervare,seat);
        seatDBRepository.save(s);
        notifyClients();
    }


    //    public Map<Competition,Integer> getAllCompetitionsWithNrParticipants(){
//        ArrayList<Competition> competitions= (ArrayList<Competition>) competitionDBRepository.findAll();
//        Map<Competition,Integer> ret=new HashMap<>();
//        for (Competition competition : competitions) {
//            int count = registrationDBRepository.countRegistrationsForCompetition(competition.getId());
//            ret.put(competition,count);
//        }
//        return ret;
//    }
   /* public Map<Competition,Integer> getAllCompetitionsWithNrParticipants(){
        ArrayList<Competition> competitions = (ArrayList<Competition>) competitionDBRepository.findAll();
        Map<Competition,Integer> ret = new TreeMap<>(new CompetitionComparator());
        for (Competition competition : competitions) {
            int count = registrationDBRepository.countRegistrationsForCompetition(competition.getId());
            ret.put(competition, count);
        }
        return ret;
    }*/
    /*private static class CompetitionComparator implements Comparator<Competition> {
        @Override
        public int compare(Competition c1, Competition c2) {
            // Comparăm mai întâi după distanță
            int distanceComparison = Long.compare(c1.getId(), c2.getId());
            if (distanceComparison != 0) {
                return distanceComparison; // Returnăm rezultatul dacă distanțele sunt diferite
            } else {
                // Dacă distanțele sunt egale, comparăm după stil
                return c1.getStyle().compareTo(c2.getStyle());
            }
        }
    }*/

   /* public Map<Participant,List<Long>> getAllParticipantsByCompetition(Long  id){
        List<Long> competitions =  registrationDBRepository.findParticipantsByCompetition(id);
        Map<Participant,List<Long>> ret = new HashMap<>();
        for (Long idParticipant : competitions) {
            Participant participant=participantDBRepository.findOne(idParticipant).get();
            ret.put(participant,registrationDBRepository.findCompetitionsByParticipant(participant.getId()));
        }
        return ret;
    }*/

    /*public void addParticipant(Participant participant,List<Long> competitions){
        participantDBRepository.save(participant);
        participant=participantDBRepository.findByNameAndBirthDate(participant.getName(),participant.getBirthDate());

        for (Long competition : competitions) {
            Registration registration=new Registration(participant,competitionDBRepository.findOne(competition).get());
            registrationDBRepository.save(registration);
        }

    }*/
    public void addRezervare(Rezervare rezervare){
        rezervareDBRepository.save(rezervare);
    }
    public List<Cursa> getCurse()
    {
        return cursaDBRepository.findAll();
    }
    public List<Rezervare> getRezervari()
    {
        return rezervareDBRepository.findAll();
    }

    public void addSeat(Seat seat) {
        seatDBRepository.save(seat);
    }

    public User findbyUsername(String username) {
        return userDBRepository.findByUsername(username);
    }

    public Rezervare findbyrezervare(User user, Cursa cursa, Integer locuri) {
        return rezervareDBRepository.findByRezervare(user,cursa,locuri);
    }

    public List<Seat> getSeat(Rezervare r) {
        return seatDBRepository.findByRezervare(r);
    }


    @Override
    public User connect(String username, String password, IRezervareObserver client) {
        List<User>users=userDBRepository.findAll();
        System.out.println("---");
        for (User t:users) {
            System.out.println(t.toString());
        }

        System.out.println("----===--");
        User user=userDBRepository.findByUsername(username);
        if (user!=null && user.getPassword().equals(password)) {

            if (loggedClients.get(username) != null) {
                throw new IllegalArgumentException("Utilizatorul este deja autentificat");
            }
            loggedClients.put(username, client);
            return user;
        }
        return null;
    }

    @Override
    public Rezervare findRezervare(User user, Cursa cursa, Integer locuri) {
        Rezervare rezervare=rezervareDBRepository.findByRezervare(user,cursa,locuri);
        if(rezervare!=null)
            return rezervare;
        return null;
    }


    @Override
    public void logout(String username) {
        if (loggedClients.get(username) == null)
            throw new IllegalArgumentException("Utilizatorul nu este autentificat");
        loggedClients.remove(username);
    }




    private void notifyClients(){
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);
        for(var client : loggedClients.values()){
            if(client == null)
                continue;
            executor.execute(client::registerRezervare);
        }
    }
}