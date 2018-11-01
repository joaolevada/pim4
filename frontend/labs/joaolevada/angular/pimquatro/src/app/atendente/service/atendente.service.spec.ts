import { TestBed } from '@angular/core/testing';

import { AtendenteService } from './atendente.service';

describe('AtendenteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AtendenteService = TestBed.get(AtendenteService);
    expect(service).toBeTruthy();
  });
});
