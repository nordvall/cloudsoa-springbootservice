package cloudsoa.service.springboot

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
@RequestMapping("/items")
class ItemController {
    private static List<Item> items = []
    private static int counter

    @RequestMapping(method = RequestMethod.GET)
    public List<Item> list() {
        return items
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
    public ResponseEntity<Item> create(@Valid @RequestBody Item employee){
        employee.id = ++counter
        employee.modified_at = new Date()
        items.add(employee)

        return new ResponseEntity<Item>(employee, HttpStatus.CREATED)
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Item> update(@PathVariable int id, @Valid @RequestBody Item employee){
        Item old = items.find { it.id == id }

        if (old) {
            old.text = employee.text
            old.modified_at = new Date()
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
            return new ResponseEntity(HttpStatus.OK)
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
