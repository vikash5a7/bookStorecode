import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RatedbooksComponent } from './ratedbooks.component';

describe('RatedbooksComponent', () => {
  let component: RatedbooksComponent;
  let fixture: ComponentFixture<RatedbooksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RatedbooksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RatedbooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
