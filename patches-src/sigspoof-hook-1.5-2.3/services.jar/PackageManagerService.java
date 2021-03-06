/*
 * Copyright (C) 2016 Lanchon <https://github.com/Lanchon>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.server;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageParser;

import com.android.server.pm.GeneratePackageInfoHookAccessor;

import lanchon.dexpatcher.annotation.*;

@DexEdit(onlyEditMembers = true)
public class PackageManagerService /* extends IPackageManager.Stub */ {

    @DexIgnore
    /* final */ Context mContext;

    @DexWrap
    PackageInfo generatePackageInfo(PackageParser.Package p, int flags) {
        PackageInfo pi = generatePackageInfo(p, flags);
        if (p != null && pi != null) pi = GeneratePackageInfoHookAccessor.hook(pi, mContext, p, flags, -1);
        return pi;
    }

}
