import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChamadoCardComponent } from './chamado-card.component';

describe('ChamadoCardComponent', () => {
  let component: ChamadoCardComponent;
  let fixture: ComponentFixture<ChamadoCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChamadoCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChamadoCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
