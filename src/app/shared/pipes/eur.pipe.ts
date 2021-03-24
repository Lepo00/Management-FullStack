import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'eur'
})
export class EurPipe implements PipeTransform {

  transform(value: string): String {
    return value+" €";
  }

}
