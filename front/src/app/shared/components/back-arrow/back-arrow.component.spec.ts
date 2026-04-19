import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackArrowComponent } from './back-arrow.component';

describe('BackArrowComponent', () => {
  let component: BackArrowComponent;
  let fixture: ComponentFixture<BackArrowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BackArrowComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BackArrowComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
