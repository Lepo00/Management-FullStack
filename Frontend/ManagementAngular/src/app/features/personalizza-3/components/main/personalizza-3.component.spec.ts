import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Personalizza3Component } from './personalizza-3.component';

describe('Personalizza3Component', () => {
  let component: Personalizza3Component;
  let fixture: ComponentFixture<Personalizza3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Personalizza3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Personalizza3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});