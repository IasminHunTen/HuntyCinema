package com.example.huntycinema.services.cinema_server.users.tickets;

import com.example.huntycinema.services.cinema_server.MyApiRequest;
import com.example.huntycinema.services.cinema_server.users.UsersApi;
import com.example.huntycinema.utils.CallBackHandler;
import com.example.huntycinema.utils.ResponseHandler;

import retrofit2.Call;

public class TicketClient extends MyApiRequest {
    private UsersApi ticketClient;
    public TicketClient() {
        ticketClient = retrofit.create(UsersApi.class);
    }

    public void createTicket(String token, String schedule_id, String configuration, ResponseHandler<TicketResp> responseHandler){
        Call<TicketResp> call = ticketClient.create_ticket(token, new TicketReq(schedule_id, configuration));
        call.enqueue(new CallBackHandler<>(responseHandler));
    }

    public void deleteTicket(String token, String ticket_id, ResponseHandler<Amount> responseHandler){
        Call<Amount> call = ticketClient.delete_ticket(token, new TicketId(ticket_id));
        call.enqueue(new CallBackHandler<>(responseHandler));
    }
}
