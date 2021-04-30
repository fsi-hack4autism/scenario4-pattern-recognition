import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadTherapyComponent } from './upload-therapy.component';

describe('UploadTherapyComponent', () => {
  let component: UploadTherapyComponent;
  let fixture: ComponentFixture<UploadTherapyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadTherapyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadTherapyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
