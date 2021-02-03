import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Personalizza4Component } from './personalizza-4.component';

describe('PersonalizzaComponent', () => {
  let component: Personalizza4Component;
  let fixture: ComponentFixture<Personalizza4Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Personalizza4Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Personalizza4Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});