import { LayoutModule } from '@angular/cdk/layout';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {
  MatButtonModule,
  MatCardModule,
  MatGridListModule,
  MatIconModule,
  MatMenuModule,
} from '@angular/material';

import { ReadAtendenteAllComponent } from './read-atendente-all.component';

describe('ReadAtendenteAllComponent', () => {
  let component: ReadAtendenteAllComponent;
  let fixture: ComponentFixture<ReadAtendenteAllComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ReadAtendenteAllComponent],
      imports: [
        NoopAnimationsModule,
        LayoutModule,
        MatButtonModule,
        MatCardModule,
        MatGridListModule,
        MatIconModule,
        MatMenuModule,
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReadAtendenteAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
