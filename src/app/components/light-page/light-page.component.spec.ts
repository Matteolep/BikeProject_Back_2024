import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LightPageComponent } from './light-page.component';

describe('LightPageComponent', () => {
  let component: LightPageComponent;
  let fixture: ComponentFixture<LightPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LightPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LightPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
