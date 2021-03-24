import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'perc'
})
export class PercPipe implements PipeTransform {

  transform(value: string): string {
    return value+" %";
  }

}
