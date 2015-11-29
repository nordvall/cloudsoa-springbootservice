package cloudsoa.service.springboot

import javax.validation.constraints.NotNull

class Item {
    int id

    @NotNull
    String text

    String modified_by
    Date modified_at
}
