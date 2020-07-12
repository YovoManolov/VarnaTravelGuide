/*
 * Copyright (c) 2019, Okta, Inc. and/or its affiliates. All rights reserved.
 * The Okta software accompanied by this notice is provided pursuant to the Apache License,
 * Version 2.0 (the "License.")
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and limitations under the
 * License.
 */

package com.example.yovo_user.varnatravelguide.rxClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.okta.oidc.AuthenticationPayload;
import com.okta.oidc.clients.BaseAuth;
import com.okta.oidc.storage.security.EncryptionManager;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * The Authentication client for logging in using a web browser.
 *
 * For login using sessionToken
 * {@link RxWebAuthClient}
 */
public interface RxWebAuthClient extends BaseAuth<RxSessionClient> {

    Single<Boolean> isInProgress();

    Completable logIn(@NonNull FragmentActivity activity, AuthenticationPayload payload);

    Completable signOutOfOkta(@NonNull FragmentActivity activity);

    Observable<RxWebAuthClientImpl.RxResult> registerListener(FragmentActivity activity);

    Completable migrateTo(@NonNull EncryptionManager manager);
}