import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdergreetingComponent } from './ordergreeting.component';

describe('OrdergreetingComponent', () => {
  let component: OrdergreetingComponent;
  let fixture: ComponentFixture<OrdergreetingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdergreetingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdergreetingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
