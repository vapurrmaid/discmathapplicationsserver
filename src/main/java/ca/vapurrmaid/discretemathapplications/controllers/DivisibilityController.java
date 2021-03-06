package ca.vapurrmaid.discretemathapplications.controllers;

import ca.vapurrmaid.discretemathapplications.domain.NaturalNumber;
import ca.vapurrmaid.discretemathapplications.error.NaturalNumberException;
import ca.vapurrmaid.discretemathapplications.services.DivisibilityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST Controller for {@link DivisibilityService}s.
 *
 * @author vapurrmaid
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/applications/divisibility")
public final class DivisibilityController {

    @Autowired
    private final DivisibilityServiceImpl divisibilityService;

    @RequestMapping(
            value = "/{multiple}",
            method = RequestMethod.GET,
            params = "number",
            produces = "application/json"
    )
    public ResponseEntity divisibilityTest(@PathVariable("multiple") Integer multiple, @RequestParam("number") Integer number) {
        try {
            NaturalNumber n = new NaturalNumber(number);

            switch (multiple) {
                case 2:
                    return new ResponseEntity<>(divisibilityService.isNumberDivisibleByTwo(n), HttpStatus.OK);
                case 3:
                    return new ResponseEntity<>(divisibilityService.isNumberDivisibleByThree(n), HttpStatus.OK);
                case 4:
                    return new ResponseEntity<>(divisibilityService.isNumberDivisibleByFour(n), HttpStatus.OK);
                default:
                    return new ResponseEntity<>(String.format("supplied multiple %d not supported", multiple), HttpStatus.BAD_REQUEST);
            }
        } catch (NaturalNumberException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
