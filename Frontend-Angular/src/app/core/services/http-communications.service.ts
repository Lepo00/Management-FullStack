import {HttpClient, HttpEvent, HttpEventType, HttpHeaders, HttpParams, HttpRequest, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';

@Injectable()
export class HttpCommunicationsService {

  private host = 'http://localhost:8080/management/';

  constructor(private httpClient: HttpClient) {
  }

  retrievePostCall<T>(endpoint: string, body: any): Observable<T> {
    return this.retrieveHttpCall<T>(new HttpRequest<T>('POST', this.host + endpoint, body));
  }

  retrievePutCall<T>(endpoint: string, body: any): Observable<T> {
    return this.retrieveHttpCall<T>(new HttpRequest<T>('PUT', this.host + endpoint, body));
  }

  retrieveGetCall<T>(endpoint: string, params: { [key: string]: string } = null): Observable<T> {
    return this.retrieveHttpCall<T>(new HttpRequest<T>('GET', this.host + endpoint, params ? {
      params: new HttpParams({
        fromObject: params
      })
    } : undefined));
  }

  retrieveDeleteCall<T>(endpoint: string): Observable<T> {
    return this.retrieveHttpCall<T>(new HttpRequest<T>('DELETE', this.host + endpoint));
  }

  private retrieveHttpCall<T>(httpRequest: HttpRequest<T>): Observable<T> {
    httpRequest = httpRequest.clone({
      responseType: 'json'
    });
    return this.httpClient.request(httpRequest).pipe(
      filter((response: HttpEvent<T>) => response.type === HttpEventType.Response),
      map((response: HttpResponse<T>) => {
        return response.body;
      })
    );
  }

  downloadXlsx<T>(endpoint:string): Observable<Blob>{
    return this.httpClient.get(this.host + endpoint, { responseType: 'blob' })
  };

  sendMail(url: string, body: { name: any; replyto: any; message: any; }, arg2: {
    headers: import("@angular/common/http").HttpHeaders;
  }) {
    return this.httpClient.post(url,body,arg2); 
  }

}
