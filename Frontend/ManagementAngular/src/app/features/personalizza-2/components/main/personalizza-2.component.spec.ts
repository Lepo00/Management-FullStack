import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Personalizza2Component } from './personalizza-2.component';

describe('PersonalizzaComponent', () => {
  let component: Personalizza2Component;
  let fixture: ComponentFixture<Personalizza2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Personalizza2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Personalizza2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});