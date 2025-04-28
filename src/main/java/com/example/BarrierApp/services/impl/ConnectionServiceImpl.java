package com.example.BarrierApp.services.impl;

import com.example.BarrierApp.models.Connection;
import com.example.BarrierApp.repositories.ConnectionRepository;
import com.example.BarrierApp.services.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;

    @Override
    public List<Connection> findAll() {
        return connectionRepository.findAll();
    }
}
