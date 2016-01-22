package cloudsoa.service.springboot

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid
import java.security.Principal

@RestController
@RequestMapping("/items")
class ItemController {
    private static List<Item> items = []
    private static int counter

    @RequestMapping(method = RequestMethod.GET)
    public Map list() {
        return [ items: items ]
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> get(@PathVariable int id) {
        Item employee = items.find { it.id == id }

        if (employee) {
            return new ResponseEntity<Item>(employee, HttpStatus.OK)
        } else {
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Item> create(@Valid @RequestBody Item employee, Principal principal){
        employee.id = ++counter
        employee.modified_at = new Date()
        employee.modified_by = principal?.name
        items.add(employee)

        return new ResponseEntity<Item>(employee, HttpStatus.CREATED)
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Item> update(@PathVariable int id, @Valid @RequestBody Item employee, Principal principal){
        Item old = items.find { it.id == id }

        if (old) {
            old.text = employee.text
            old.modified_at = new Date()
            old.modified_by = principal?.name
            return new ResponseEntity<Item>(HttpStatus.OK)
        } else {
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id){
        Item employee = items.find { it.id == id }

        if (employee) {
            items.remove(employee)
            return new ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
