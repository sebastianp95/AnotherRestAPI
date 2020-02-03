package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
public class VehicleController {

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        FileWriter output = new FileWriter("./inventory.txt", true);
        mapper.writeValue(output, newVehicle);
        FileUtils.writeStringToFile(new File("./inventory.txt"),
                System.lineSeparator(), "UTF-8", true);
        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {

        List<String> lines = FileUtils.readLines(new File("./inventory.txt"), "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        Vehicle found = null;
        for (String line : lines) {
            Vehicle v = mapper.readValue(line, Vehicle.class);
            if (v.getId() == id) {
                found = v;
            }
        }
        return found;
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        List<String> lines = FileUtils.readLines(new File("./inventory.txt"), "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
       
        for (String line : lines) {
            Vehicle v = mapper.readValue(line, Vehicle.class);
            if (v.getId() == id) {

                org.apache.commons.io.FileUtils.writeStringToFile( new File ("./inventory.txt"), "", "UTF-8");
                return new ResponseEntity<String>("Found", HttpStatus.FOUND);
            } else {
                return new ResponseEntity<String>("Sorry! Vehicle not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>("Sorry", HttpStatus.FOUND);
    }
}
