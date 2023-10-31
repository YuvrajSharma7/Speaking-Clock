package speaking.clock.Speaking.Clock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import speaking.clock.Speaking.Clock.Utilities.Constants;
import speaking.clock.Speaking.Clock.service.SpeakingClockService;


/**
 * This program converts a given 24-hour format into words.
 * @author Yuvraj Sharma
 */
@RestController
@RequestMapping(Constants.API)
@Slf4j
public class SpeakingClockController {

    @Autowired
    SpeakingClockService speakingClockService;
    @GetMapping(value = Constants.CURRENT_TIME,produces = Constants.APPLICATION_JSON )
    public ResponseEntity<?> geCurrentTime(){

        log.info("url : "+Constants.API+Constants.CURRENT_TIME);
        return new ResponseEntity<>(this.speakingClockService.getCurrentTime(),HttpStatus.OK);
    }

    @GetMapping(value = Constants.TIME , produces = Constants.APPLICATION_JSON )
    public ResponseEntity<?> geTime(@RequestParam String time){
        log.info("url : "+Constants.API+Constants.TIME);
        log.debug("time input : {}", time);
        return new ResponseEntity<>(this.speakingClockService.validateTime(time),HttpStatus.OK);
    }
}
