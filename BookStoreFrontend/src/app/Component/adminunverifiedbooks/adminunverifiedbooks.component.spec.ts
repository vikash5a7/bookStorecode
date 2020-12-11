import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminunverifiedbooksComponent } from './adminunverifiedbooks.component';

describe('AdminunverifiedbooksComponent', () => {
  let component: AdminunverifiedbooksComponent;
  let fixture: ComponentFixture<AdminunverifiedbooksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminunverifiedbooksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminunverifiedbooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
