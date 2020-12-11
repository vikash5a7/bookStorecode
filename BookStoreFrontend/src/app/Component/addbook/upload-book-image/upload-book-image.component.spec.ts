import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadBookImageComponent } from './upload-book-image.component';

describe('UploadBookImageComponent', () => {
  let component: UploadBookImageComponent;
  let fixture: ComponentFixture<UploadBookImageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadBookImageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadBookImageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
