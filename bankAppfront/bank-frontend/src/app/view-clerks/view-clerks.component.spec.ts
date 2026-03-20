import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewClerksComponent } from './view-clerks.component';

describe('ViewClerksComponent', () => {
  let component: ViewClerksComponent;
  let fixture: ComponentFixture<ViewClerksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewClerksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewClerksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
