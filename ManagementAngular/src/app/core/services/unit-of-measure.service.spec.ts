import { TestBed } from '@angular/core/testing';

import { UnitOfMeasureService } from './unit-of-measure.service';

describe('UniOfMeasureService', () => {
  let service: UnitOfMeasureService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnitOfMeasureService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
