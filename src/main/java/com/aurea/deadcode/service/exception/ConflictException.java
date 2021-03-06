package com.aurea.deadcode.service.exception;

import com.aurea.deadcode.model.ScmRepo;

public class ConflictException extends ServiceException {
    private static final long serialVersionUID = -7452423129979194560L;

    public ConflictException(final ScmRepo repo, final Throwable t) {
        super("Repo " + repo + " already exists", 409);
    }

}
