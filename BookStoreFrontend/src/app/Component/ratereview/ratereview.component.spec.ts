import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RatereviewComponent } from './ratereview.component';

describe('RatereviewComponent', () => {
  let component: RatereviewComponent;
  let fixture: ComponentFixture<RatereviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RatereviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RatereviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
