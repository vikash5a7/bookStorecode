import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GiverateComponent } from './giverate.component';

describe('GiverateComponent', () => {
  let component: GiverateComponent;
  let fixture: ComponentFixture<GiverateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GiverateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GiverateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
