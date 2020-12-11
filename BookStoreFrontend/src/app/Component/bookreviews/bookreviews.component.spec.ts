import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookreviewsComponent } from './bookreviews.component';

describe('BookreviewsComponent', () => {
  let component: BookreviewsComponent;
  let fixture: ComponentFixture<BookreviewsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookreviewsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookreviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
