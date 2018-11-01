import { TestBed } from '@angular/core/testing';

import { HttpAuthClientService } from './http-auth-client.service';

describe('HttpAuthClientService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpAuthClientService = TestBed.get(HttpAuthClientService);
    expect(service).toBeTruthy();
  });
});
