package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.AccountRepository;
import com.savle.togethersaving.repository.ChallengeUserRepository;
import com.savle.togethersaving.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    // TODO : transaction?
    public void refundSavingAmount(ChallengeUser challengeUser) {
        long accumulatedBalance = challengeUser.getAccumulatedBalance();
        Long userId = challengeUser.getUser().getUserId();
        Account cmaAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.CMA);
        Account physicalAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.PHYSICAL);
        cmaAccount.withdraw(accumulatedBalance);
        physicalAccount.deposit(accumulatedBalance);
    }

    // TODO : transaction?
    public void refundEntryFee(ChallengeUser challengeUser) {
        Long entryFee = challengeUser.getChallenge().getEntryFee();
        Account userPhysicalAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(challengeUser.getUser().getUserId(), AccountType.PHYSICAL);

        Account companyCMAAccount = getAdminAccount();
        companyCMAAccount.withdraw(entryFee);
        userPhysicalAccount.deposit(entryFee);
    }

    // TODO : transaction?
    public void depositReward(ChallengeUser winner, long reward) {
        Account winnerPhysicalAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(winner.getUser().getUserId(), AccountType.PHYSICAL);
        Account companyCMAAccount = getAdminAccount();
        reward =  (long)(reward * 0.95);
        companyCMAAccount.withdraw(reward);
        winnerPhysicalAccount.deposit(reward);
    }

    private Account getAdminAccount() {
        return accountRepository.findAccountByOwner_UserIdAndAccountType(userRepository.getUserByRole("ADMIN").getUserId(), AccountType.CMA);
    }
}
