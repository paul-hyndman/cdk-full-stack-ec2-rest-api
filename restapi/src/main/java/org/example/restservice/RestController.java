package org.example.restservice;

import org.example.domain.NameFinder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
@Component
public class RestController {

    @GetMapping(
            value = "/mostpopular/{input}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getMostPopular(@PathVariable String input) {
        String[] names = input.split(",");
        NameFinder nameFinder = new NameFinder(names);
        return new ResponseEntity<>(nameFinder.getMostPopular(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/leastpopular/{input}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getLeastPopular(@PathVariable String input) {
        String[] names = input.split(",");
        NameFinder nameFinder = new NameFinder(names);
        return new ResponseEntity<>(nameFinder.getLeastPopular(), HttpStatus.OK);
    }
}
