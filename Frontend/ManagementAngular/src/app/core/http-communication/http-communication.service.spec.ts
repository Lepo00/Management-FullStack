import { TestBed } from '@angular/core/testing';

import { HttpCommunicationsService } from './http-communication.service';

describe('HttpCommunicationService', () => {
  let service: HttpCommunicationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpCommunicationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
