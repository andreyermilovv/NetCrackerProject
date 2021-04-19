package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.AirplaneDto;
import com.netcracker.airlines.entities.Airplane;
import com.netcracker.airlines.mapper.AirplaneMapper;
import com.netcracker.airlines.repository.AirplaneRepo;
import com.netcracker.airlines.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepo airplaneRepo;

    private final AirplaneMapper airplaneMapper;

    @Override
    public Airplane getOne(Long id) {
        return airplaneRepo.getOne(id);
    }

    @Override
    public List<Airplane> getAll() {
        return airplaneRepo.findAll();
    }

    @Override
    public void save(AirplaneDto airplaneDto) {
        Airplane airplane = airplaneMapper.toCreate(airplaneDto);
        airplaneRepo.save(airplane);
    }

    @Override
    public void delete(Long id) {
        Airplane airplane = getOne(id);
        airplaneRepo.delete(airplane);
    }

    @Override
    public void edit(Long id, AirplaneDto airplaneDto) {
        Airplane airport = getOne(id);
        Airplane edited = airplaneMapper.toEdit(airplaneDto, airport);
        airplaneRepo.save(edited);
    }
}
