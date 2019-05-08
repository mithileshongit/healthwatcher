package com.maitkon.healthwatcher;

public interface PermissionResult

{
    void permissionGranted();

    void permissionDenied();

    void permissionForeverDenied();
}
