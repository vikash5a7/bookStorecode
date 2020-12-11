import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplaybookComponent } from './displaybook.component';

describe('DisplaybookComponent', () => {
  let component: DisplaybookComponent;
  let fixture: ComponentFixture<DisplaybookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplaybookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplaybookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
