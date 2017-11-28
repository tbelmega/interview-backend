package de.bringmeister.util

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item with this ID does not exist.")
class NotFoundException: RuntimeException() {
}