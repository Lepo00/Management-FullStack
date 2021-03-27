import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateCustom'
})
export class DateCustomPipe implements PipeTransform {
  //from italian to US format
  transform(date: string): string {
    let day=date.substring(0,2);
    let month=date.substring(3,5);
    let year=date.substring(6,10);
    return year+"-"+month+"-"+day;
  }

}
